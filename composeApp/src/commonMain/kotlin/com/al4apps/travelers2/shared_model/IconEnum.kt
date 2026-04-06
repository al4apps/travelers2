package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.checkmark
import travelers2.composeapp.generated.resources.close
import travelers2.composeapp.generated.resources.minus
import travelers2.composeapp.generated.resources.moon
import travelers2.composeapp.generated.resources.star

enum class IconEnum(val drawableRes: DrawableResource) {
    NONE(drawableRes = Res.drawable.close),
    CHECKED(drawableRes = Res.drawable.checkmark),
    STAR(drawableRes = Res.drawable.moon),
    LIKE(drawableRes = Res.drawable.star),
    BRICK(drawableRes = Res.drawable.minus)
}
