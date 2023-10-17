package de.dannyb.imnuri.data.repository

import de.dannyb.imnuri.data.local.HymnDao
import de.dannyb.imnuri.data.mapper.HymnDtoToEntityMapper
import de.dannyb.imnuri.data.mapper.HymnEntityToModelMapper
import de.dannyb.imnuri.data.remote.ImnuriService
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.repository.HymnsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class HymnsRepositoryImpl @Inject constructor(
    private val api: ImnuriService,
    private val dao: HymnDao,
    private val hymnsDtoToEntityMapper: HymnDtoToEntityMapper,
    private val hymnsEntityToModelMapper: HymnEntityToModelMapper,
) : HymnsRepository {

    override suspend fun getAllHymns(query: String?): List<HymnModel> {
        if (dao.getNumberOfHymns() == 0) {
            fetchHymnsAndFeedDb()
        }

        val hymns = if(query == null) dao.getAllHymns() else {
            if(query.toIntOrNull() != null) {
                dao.searchHymnsByNumber(query.toInt())
            } else {
                dao.searchHymnsByTitle(query)
            }
        }

        return hymnsEntityToModelMapper.mapAll(hymns)
    }

    override suspend fun getHymn(number: Int): HymnModel {
        return hymnsEntityToModelMapper.map(dao.getHymnByNumber(number))
    }

    private suspend fun fetchHymnsAndFeedDb() = withContext(Dispatchers.IO) {
        val locale = validateLanguage(Locale.getDefault().language)
        val hymnsFromApi = api.getHymns(locale).hymns
        val hymnsEntities = hymnsDtoToEntityMapper.mapAll(hymnsFromApi)
        dao.insertAll(hymnsEntities)
    }

    private fun validateLanguage(language: String): String = when (language) {
        "it" -> "it"
        "de" -> "de"
        else -> "ro"
    }
}