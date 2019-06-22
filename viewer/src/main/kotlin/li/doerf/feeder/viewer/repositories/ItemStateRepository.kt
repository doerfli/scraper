package li.doerf.feeder.viewer.repositories

import li.doerf.feeder.common.entities.Feed
import li.doerf.feeder.common.entities.Item
import li.doerf.feeder.viewer.entities.ItemState
import li.doerf.feeder.viewer.entities.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ItemStateRepository : CrudRepository<ItemState, Long> {
    fun findByUserAndFeedAndItem(user: User, feed: Feed, item: Item): Optional<ItemState>
    fun findAllByUserAndFeed_Pkey(user: User, feedPkey: Long): List<ItemState>
}