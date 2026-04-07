package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.features.home.create_trip.CreateTripComponent
import com.al4apps.travelers2.features.home.profile.ProfileComponent
import com.al4apps.travelers2.features.home.search_trips.SearchTripsComponent

sealed interface HomeChild {
    data class SearchTrips(
        val component: SearchTripsComponent,
    ) : HomeChild

    data class CreateTrip(
        val component: CreateTripComponent,
    ) : HomeChild

    data class Profile(
        val component: ProfileComponent,
    ) : HomeChild
}
