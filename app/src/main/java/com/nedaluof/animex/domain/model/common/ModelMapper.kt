package com.nedaluof.animex.domain.model.common

/**
 * Created By NedaluOf - 7/7/2023.
 */
interface ModelMapper<INPUT_MODEL, OUTPUT_MODEL> {
  /**
   * handle mapping from single object
   * */
  fun fromModel(inputModel: INPUT_MODEL): OUTPUT_MODEL?
  
  /**
   * handle mapping to list of object
   * */
  fun fromModelList(list: List<INPUT_MODEL>?): List<OUTPUT_MODEL> {
    return list?.mapNotNull { fromModel(it) } ?: listOf()
  }
}