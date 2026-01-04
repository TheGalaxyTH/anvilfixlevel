# Changelog

所有重要的版本变更都会记录在这个文件中。

格式基于 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.0.0/)。

## [1.0.0] - 2026-01-04

### 新增
- 铁砧经验消耗固定为可配置的值（默认 1 级）
- 自动移除物品的累计修复成本，防止"过于昂贵！"提示
- 配置文件支持自定义经验消耗等级（0-100）
- 配置文件支持开关修复成本移除功能

### 特性
- 使用 Mixin 技术直接修改原版铁砧逻辑
- 支持所有类型的铁砧操作（修复、合并附魔书、重命名等）
- 完全保留原版的附魔等级和物品属性
- 无限次修复/合并物品而不会变得过于昂贵

### 技术细节
- 基于 NeoForge 21.1.217+
- 支持 Minecraft 1.21.1
- 使用 Mixin 修改 `AnvilMenu.createResult()` 方法
- 移除 40 级经验上限检查

[1.0.0]: https://github.com/TheGalaxyTH/anvilfixlevel/releases/tag/v1.0.0
