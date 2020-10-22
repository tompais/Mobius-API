package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "category",
    visible = true
)
@JsonSubTypes(
    Type(value = OrientationTaskAnswerRequest::class, name = "orientation"),
    Type(value = FixationTaskAnswerRequest::class, name = "fixation")
)
open class GameAnswersRequest(
    val category: Game.Category,

    val gameId: Long
)
