package de.dannyb.imnuri.data.utils

internal class InitProvider : BaseContentProvider() {
    override fun onCreate(): Boolean {
        DataModuleInjector.init(context = checkNotNull(context))
        return true
    }
}