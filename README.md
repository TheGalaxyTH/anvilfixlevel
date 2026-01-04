# AnvilFixLevel

<div align="center">

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.217+-orange.svg)](https://neoforged.net/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

一个用于 Minecraft 1.21.1 NeoForge 的模组，修复铁砧的经验消耗机制。

[English](#english) | [中文](#中文)

</div>

---

## 中文

### 功能特性

- ✨ **固定经验消耗**: 铁砧的每次操作消耗固定的经验等级，而不是累计增加
- 🚫 **移除"过于昂贵"**: 完全移除铁砧的修复成本累计惩罚，不会再出现"Too Expensive!"提示
- ⚙️ **可配置**: 通过配置文件自定义经验消耗等级
- 🔄 **无限修复**: 可以无限次修复和合并物品，不受 40 级经验上限限制
- 🎯 **完全兼容**: 保留原版的附魔等级、物品属性和所有铁砧功能

### 安装

1. 确保已安装 [Minecraft 1.21.1](https://www.minecraft.net/) 和 [NeoForge 21.1.217+](https://neoforged.net/)
2. 下载最新版本的模组 jar 文件
3. 将 jar 文件放入游戏目录的 `mods` 文件夹
4. 启动游戏

### 配置说明

配置文件位于 `config/anvilfixlevel-common.toml`

```toml
# 铁砧操作的固定经验等级消耗。设置为 0 可禁用所有消耗。
# 此配置替代原版的累计修复成本系统。
# Range: 0 ~ 100
anvilCost = 1

# 是否移除物品的修复成本惩罚。
# 设置为 true 可防止"过于昂贵！"消息出现。
removeRepairCost = true
```

#### 配置项说明

- **anvilCost** (默认: 1)
  - 铁砧操作的固定经验等级消耗
  - 范围: 0-100
  - 设置为 0 可完全禁用经验消耗

- **removeRepairCost** (默认: true)
  - 是否移除物品的修复成本惩罚
  - 设置为 true 可防止"Too Expensive!"消息出现

### 工作原理

原版 Minecraft 的铁砧系统会在每次修复或合并物品时增加累计修复成本，导致物品在多次修复后变得"过于昂贵"而无法继续操作。

此模组通过以下方式解决这个问题：

1. 使用 Mixin 直接修改 `AnvilMenu.createResult()` 方法
2. 移除 40 级经验的硬性上限检查
3. 将经验等级消耗设置为配置的固定值
4. 移除输出物品上的修复成本标签
5. 保持原版的材料消耗和附魔逻辑不变

### 使用示例

#### 修复工具
- 将损坏的工具放入铁砧左侧
- 放入修复材料（如钻石）到右侧
- 只需 1 级经验（或您配置的值）即可修复
- 无限次修复，永不"过于昂贵"

#### 合并附魔书
- 将两本附魔书放入铁砧
- 无论附魔等级多高，都只消耗固定经验
- 可以无限次合并，获得顶级附魔

#### 给装备附魔
- 将装备和附魔书放入铁砧
- 附魔等级完全保留（如锋利 V 就是锋利 V）
- 固定经验消耗，可重复操作

### 兼容性

- **Minecraft**: 1.21.1
- **NeoForge**: 21.1.217+
- **Java**: 21+
- **客户端**: 必需
- **服务端**: 必需

### 构建

```bash
./gradlew build
```

构建完成后，jar 文件将位于 `build/libs/` 目录下。

### 许可证

本项目采用 [MIT License](LICENSE) 许可证。

### 作者

TheGalaxy

### 反馈与支持

如果您遇到任何问题或有建议，请在 [GitHub Issues](https://github.com/TheGalaxyTH/anvilfixlevel/issues) 提交。

---

## English

### Features

- ✨ **Fixed Experience Cost**: Anvil operations cost a fixed amount of experience levels instead of increasing cumulatively
- 🚫 **Remove "Too Expensive"**: Completely removes the cumulative repair cost penalty, preventing the "Too Expensive!" message
- ⚙️ **Configurable**: Customize experience cost through configuration file
- 🔄 **Unlimited Repairs**: Repair and combine items unlimited times without the 40-level cap
- 🎯 **Fully Compatible**: Preserves vanilla enchantment levels, item properties, and all anvil functions

### Installation

1. Ensure [Minecraft 1.21.1](https://www.minecraft.net/) and [NeoForge 21.1.217+](https://neoforged.net/) are installed
2. Download the latest mod jar file
3. Place the jar file in the `mods` folder of your game directory
4. Launch the game

### Configuration

Configuration file is located at `config/anvilfixlevel-common.toml`

```toml
# The fixed experience level cost for anvil operations. Set to 0 to disable all costs.
# This replaces the vanilla cumulative repair cost system.
# Range: 0 ~ 100
anvilCost = 1

# If true, removes the repair cost penalty from items after anvil operations.
# This prevents the 'Too Expensive!' message from ever appearing.
removeRepairCost = true
```

### How It Works

Vanilla Minecraft's anvil system adds cumulative repair costs each time you repair or combine items, eventually making items "Too Expensive" to work with.

This mod solves the problem by:

1. Using Mixin to directly modify the `AnvilMenu.createResult()` method
2. Removing the hard 40-level experience cap check
3. Setting experience cost to a configured fixed value
4. Removing repair cost tags from output items
5. Preserving vanilla material consumption and enchantment logic

### Compatibility

- **Minecraft**: 1.21.1
- **NeoForge**: 21.1.217+
- **Java**: 21+
- **Client**: Required
- **Server**: Required

### License

This project is licensed under the [MIT License](LICENSE).

### Author

TheGalaxy

### Feedback & Support

If you encounter any issues or have suggestions, please submit them at [GitHub Issues](https://github.com/TheGalaxyTH/anvilfixlevel/issues).


