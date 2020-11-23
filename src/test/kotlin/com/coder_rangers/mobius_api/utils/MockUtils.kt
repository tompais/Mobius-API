package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.requests.SignInRequest
import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.utils.TestConstants.GUARDIAN_EMAIL
import com.coder_rangers.mobius_api.utils.TestConstants.PASSWORD
import com.coder_rangers.mobius_api.utils.TestConstants.PATIENT_EMAIL
import org.springframework.util.ResourceUtils
import java.awt.image.BufferedImage
import java.time.LocalDate
import javax.imageio.ImageIO

object MockUtils {
    fun mockSignUpRequest(
        firstName: String = "Fulanito",
        lastName: String = "De Tal",
        patientEmail: String = PATIENT_EMAIL,
        guardianEmail: String = GUARDIAN_EMAIL,
        birthday: LocalDate = LocalDate.now().minusYears(20)
    ) = SignUpRequest(
        firstName,
        lastName,
        patientEmail,
        guardianEmail,
        birthday
    )

    fun mockSignInRequest(
        email: String = PATIENT_EMAIL,
        password: String = PASSWORD
    ) = SignInRequest(
        email,
        password
    )

    fun getMockBufferedImage(imageName: String): BufferedImage =
        ImageIO.read(ResourceUtils.getFile("classpath:images/$imageName"))
}
