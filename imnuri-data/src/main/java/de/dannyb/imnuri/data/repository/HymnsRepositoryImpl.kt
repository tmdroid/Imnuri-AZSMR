package de.dannyb.imnuri.data.repository

import de.dannyb.imnuri.data.local.HymnDao
import de.dannyb.imnuri.data.mapper.HymnDtoToEntityMapper
import de.dannyb.imnuri.data.mapper.HymnEntityToModelMapper
import de.dannyb.imnuri.data.remote.ImnuriService
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.repository.HymnsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class HymnsRepositoryImpl @Inject constructor(
    private val api: ImnuriService,
    private val dao: HymnDao,
    private val hymnsDtoToEntityMapper: HymnDtoToEntityMapper,
    private val hymnsEntityToModelMapper: HymnEntityToModelMapper,
) : HymnsRepository {

    override fun getAllHymns(): Flow<List<HymnModel>> = flow {
        if (dao.getNumberOfHymns() == 0) {
            fetchHymnsAndFeedDb()
        }

        val hymns = dao.getAllHymns().map { hymns ->
            hymns.map { hymnsEntityToModelMapper.map(it) }
        }
        emitAll(hymns)
    }

    private suspend fun fetchHymnsAndFeedDb() {
        val locale = Locale.getDefault().language
        val hymnsFromApi = api.getHymns(locale).hymns
        val hymnsEntities = hymnsFromApi.map { hymnsDtoToEntityMapper.map(it) }
        dao.insertAll(hymnsEntities)
    }
}