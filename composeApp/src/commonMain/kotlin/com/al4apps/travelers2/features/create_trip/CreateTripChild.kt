package com.al4apps.travelers2.features.create_trip

import com.al4apps.travelers2.features.create_trip.confirm.CreateTripConfirmComponent
import com.al4apps.travelers2.features.create_trip.route.CreateTripRouteComponent
import com.al4apps.travelers2.features.create_trip.time.CreateTripTimeComponent
import com.al4apps.travelers2.features.create_trip.vehicle.CreateTripVehicleComponent

sealed interface CreateTripChild {
    data class Route(val component: CreateTripRouteComponent) : CreateTripChild
    data class Time(val component: CreateTripTimeComponent) : CreateTripChild
    data class Vehicle(val component: CreateTripVehicleComponent) : CreateTripChild
    data class Confirm(val component: CreateTripConfirmComponent) : CreateTripChild
}
