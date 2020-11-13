package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
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
    Type(value = TextTestGameAnswersWithResultsRequest::class, name = "orientation"),
    Type(value = TextTestGameAnswersRequest::class, name = "fixation"),
    Type(value = CalculationTestGameAnswersRequest::class, name = "calculation"),
    Type(value = TextTestGameAnswersWithResultsRequest::class, name = "memory"),
    Type(value = AttentionTestGameAnswersRequest::class, name = "attention"),
    Type(value = TextTestGameAnswersRequest::class, name = "visualization"),
    Type(value = TextTestGameAnswersRequest::class, name = "comprehension")
)
open class TestGameAnswersRequest<T>(
    val category: Game.Category,

    @field:Positive
    val gameId: Long,

    @field:NotEmpty
    val patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<T>>
)
