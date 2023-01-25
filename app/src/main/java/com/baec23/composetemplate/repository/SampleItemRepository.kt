package com.baec23.composetemplate.repository

import com.baec23.composetemplate.model.SampleItem
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@ActivityScoped
class SampleItemRepository {

    private val _allSampleItems: MutableStateFlow<List<SampleItem>> = MutableStateFlow(listOf())
    val allSampleItems = _allSampleItems.asStateFlow()
    init {
        _allSampleItems.value = listOf(
            SampleItem("1,","Sample Item 1", "The quick brown fox jumps over the lazy dog"),
            SampleItem("2,","Sample Item 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit"),
            SampleItem("3,","Sample Item 3", "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"),
            SampleItem("4,","Sample Item 4", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium"),
            SampleItem("5,","Sample Item 5", "emo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt"),
            SampleItem("6,","Sample Item 6", "quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt"),
            SampleItem("7,","Sample Item 7", "Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? "),
            SampleItem("8,","Sample Item 8", " Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil"),
        )
    }
    fun getSampleItemById(id: String) : SampleItem{
        return _allSampleItems.value.first { it.id == id }
    }
}