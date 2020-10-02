package com.example.appsnelayan

import com.google.gson.annotations.SerializedName

data class IkanModel(

	@field:SerializedName("fish_name")
	val fishName: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("WA_number")
	val wANumber: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("fish_price")
	val fishPrice: Int? = null,

	@field:SerializedName("fish_image")
	val fishImage: String? = null,

	@field:SerializedName("username")
	var username: String? = null
) : Comparable<IkanModel> {
	override fun compareTo(other: IkanModel): Int {
		return (this.id - other.id) *-1
	}
}
