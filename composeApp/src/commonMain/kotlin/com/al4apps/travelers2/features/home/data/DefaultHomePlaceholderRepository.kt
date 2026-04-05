package com.al4apps.travelers2.features.home.data

import com.al4apps.travelers2.features.home.domain.model.HomePlaceholder
import com.al4apps.travelers2.features.home.domain.repository.HomePlaceholderRepository

class DefaultHomePlaceholderRepository : HomePlaceholderRepository {

    override fun getPlaceholder(): HomePlaceholder =
        HomePlaceholder(
            title = "Home",
            message = "Base Home feature is initialized and ready for further expansion.",
        )
}
