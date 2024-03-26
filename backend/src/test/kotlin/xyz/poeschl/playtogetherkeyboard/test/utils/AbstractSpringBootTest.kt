package xyz.poeschl.playtogetherkeyboard.test.utils

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * This abstract class can be inherited to start up the complete Spring Boot Context.
 */
@Disabled("Abstract class")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = ["xyz.poeschl.playtogetherkeyboard"])
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
abstract class AbstractSpringBootTest
