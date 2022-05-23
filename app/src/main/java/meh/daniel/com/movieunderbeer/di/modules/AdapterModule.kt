package meh.daniel.com.movieunderbeer.di.modules

import dagger.Module
import dagger.Provides
import meh.daniel.com.movieunderbeer.adapter.ItemTypes
import meh.daniel.com.movieunderbeer.adapter.ViewHoldersManagerImpl
import meh.daniel.com.movieunderbeer.adapter.viewHolders.FilmViewHolder
import meh.daniel.com.movieunderbeer.adapter.viewHolders.GenreViewHolder
import ru.alexmaryin.recycleronvisitor.ui.adapter.ViewHoldersManager
import ru.alexmaryin.recycleronvisitor.ui.viewHolders.HeaderViewHolder
import javax.inject.Named
import javax.inject.Singleton

@Module
class AdapterModule {


    @Named("provideAdaptersManager")
    @Provides
    fun provideAdaptersManager(): ViewHoldersManager = ViewHoldersManagerImpl().apply {
        registerViewHolder(ItemTypes.HEADER, HeaderViewHolder())
        registerViewHolder(ItemTypes.CHIP, GenreViewHolder())
        registerViewHolder(ItemTypes.CARD, FilmViewHolder())
    }


}