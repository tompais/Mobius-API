package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "category",
    visible = true
)
@JsonSubTypes(
    Type(value = TextGameAnswersWithResultsRequest::class, name = "orientation"),
    Type(value = TextGameAnswersRequest::class, name = "fixation"),
    Type(value = NumericGameAnswersRequest::class, name = "calculation"),
    Type(value = TextGameAnswersWithResultsRequest::class, name = "memory"),
    Type(value = AttentionGameAnswersRequest::class, name = "attention"),
    Type(value = TextGameAnswersRequest::class, name = "visualization"),
    Type(value = TextGameAnswersRequest::class, name = "repetition"),
    Type(value = TextGameAnswersRequest::class, name = "comprehension"),
    Type(value = NumericGameAnswersRequest::class, name = "reading"),
    Type(value = TextGameAnswersRequest::class, name = "writing"),
    Type(value = TextGameAnswersRequest::class, name = "drawing")
)
open class GameAnswersRequest<T>(
    val category: Category,

    @field:Positive
    val gameId: Long,

    val areTestGameAnswers: Boolean,

    @field:NotEmpty
    val patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<T>>
)
