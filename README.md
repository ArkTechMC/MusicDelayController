# Music Delay Controller

This mod let you control background music delay.

## Configuration

Config file is at `.minecraft/config/music_delay_controller.json`. Mod will automatically create it on first launch.
You can also create it by yourself.

```json5
{
  "bgm music id": {//All keys are optional
    "enable": true,//Should play this sound, default: true
    "minDelay": 0,//The min delay before play, default: 0
    "maxDelay": 0 //The max delay before play, default: 0
  },
  //More if you want
}
```

### Vanilla BGM Music Id

Data source: https://minecraft.wiki/w/Sounds.json
- `music.creative`: BGM in creative mode.
- `music.credits`: BGM in the credit screen.
- `music.dragon`: BGM when fighting ender dragon.
- `music.end`: BGM in the end.
- `music.game`: BGM in survival mode.
- `music.menu`: BGM in title screen.
- `music.under_water`: BGM when in water.