package com.greatorator.tolkienmobs.handler.interfaces;

import com.brandon3055.brandonscore.blocks.TileBCBase;

public interface IKeyAccessTile {
    TileBCBase getTile();

    boolean hasCK();

    boolean consumeKey();

    boolean hasMode();

//    TileKeyStone.Mode mode();

    String getCode();

    boolean hasDelay();

    int getDelay();
}
