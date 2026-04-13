package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.features.create_trip.CreateTripComponent
import com.al4apps.travelers2.features.profile.ProfileComponent
import com.al4apps.travelers2.features.search.SearchFeatureComponent

sealed interface HomeChild {
    data class SearchTrips(
        val component: SearchFeatureComponent,
    ) : HomeChild

    data class CreateTrip(
        val component: CreateTripComponent,
    ) : HomeChild

    data class Profile(
        val component: ProfileComponent,
    ) : HomeChild
}
