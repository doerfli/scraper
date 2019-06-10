package li.doerf.feeder.viewer.controllers

import li.doerf.feeder.common.entities.User
import li.doerf.feeder.common.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestHelper @Autowired constructor(
        val userRepository: UserRepository
){
    fun createUser( username: String): User {
        val user = User(0, username, "password", mutableListOf())
        userRepository.save(user)
        return user
    }
}