package com.imgur.upload.entity

import com.imgur.network_api.models.AccountImageItem

data class AccountItemEntity(
    val id: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val deleteHash: String = ""
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = AccountItemEntity()

        fun valueOf(value: AccountImageItem): AccountItemEntity {
            return AccountItemEntity(
                id = value.id,
                imageUrl = value.link,
                title = value.title,
                deleteHash = value.deleteHash
            )
        }
    }
}
