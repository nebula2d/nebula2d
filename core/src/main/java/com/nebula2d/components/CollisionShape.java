/*
 * Nebula2D is a cross-platform, 2D game engine for PC, Mac, & Linux
 * Copyright (c) $date.year Jon Bonazza
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nebula2d.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.nebula2d.scene.GameObject;
import com.nebula2d.scene.Transform;

/**
 * @author Jon Bonazza <jonbonazza@gmail.com>
 */
public abstract class CollisionShape {

    private Material material;

    public CollisionShape(Material material) {
        this.material = material;
    }

    public Fixture affixTo(Body body, boolean isSensor) {
        Transform transform = ((GameObject) body.getUserData()).getTransform();
        FixtureDef fixtureDef = createFixtureDef(transform, isSensor);
        return body.createFixture(fixtureDef);
    }

    protected abstract Shape getShape(Transform transform);

    private FixtureDef createFixtureDef(Transform transform, boolean isSensor) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = getShape(transform);
        fixtureDef.density = material.getDensity();
        fixtureDef.friction = material.getFriction();
        fixtureDef.restitution = material.getRestitution();
        fixtureDef.isSensor = isSensor;

        return fixtureDef;
    }
}