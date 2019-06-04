package li.doerf.feeder.viewer.services

import li.doerf.feeder.common.entities.Role
import li.doerf.feeder.common.entities.User
import li.doerf.feeder.common.repositories.UserRepository
import li.doerf.feeder.common.util.getLogger
import li.doerf.feeder.viewer.HttpException
import li.doerf.feeder.viewer.dto.UserResponseDto
import li.doerf.feeder.viewer.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManager: AuthenticationManager
){

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val log = getLogger(javaClass)
    }

    fun signin(username: String, password: String): UserResponseDto {
        try {
            // TODO security - check password even if user does not exist (timing attack)
            val auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            // TODO auth.details ... return user object if possible
            return UserResponseDto(token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).orElseThrow {throw IllegalArgumentException("invalid username $username")}.roles))
        } catch (e: AuthenticationException) {
            throw HttpException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }

    }

    fun signup(username: String, password: String): UserResponseDto {
        if (userRepository.findByUsername(username).isEmpty) {
            val user = User(0, username, passwordEncoder.encode(password), mutableListOf(Role.ROLE_CLIENT))
            // TODO security - this is strange
            userRepository.save(user)
            log.info("stored user to database: $user")
            // TODO auth.detals ... return user object
            return UserResponseDto(token = jwtTokenProvider.createToken(username, listOf(Role.ROLE_CLIENT)))
        } else {
            // TODO security - handle this better ... enumeration attack
            throw HttpException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

//    fun delete(username: String) {
//        val user = userRepository.findByUsername(username).orElseThrow { throw IllegalArgumentException("Invalid username $username") }
//        userRepository.delete(user)
//    }

}