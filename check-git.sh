#!/bin/bash

# Git 提交前检查脚本
# 用于验证将要提交的文件是否正确

echo "=========================================="
echo "Git 提交前检查"
echo "=========================================="
echo ""

# 检查是否在 Git 仓库中
if [ ! -d .git ]; then
    echo "❌ 错误：当前目录不是 Git 仓库"
    echo "请先运行: git init"
    exit 1
fi

echo "✅ 检测到 Git 仓库"
echo ""

# 检查 .gitignore 是否存在
if [ ! -f .gitignore ]; then
    echo "⚠️  警告：未找到 .gitignore 文件"
else
    echo "✅ .gitignore 文件存在"
fi

echo ""
echo "=========================================="
echo "待提交的文件："
echo "=========================================="

# 显示将要提交的文件
git add --dry-run . 2>/dev/null

echo ""
echo "=========================================="
echo "检查潜在问题："
echo "=========================================="

# 检查是否包含不应该提交的文件
ISSUES=0

# 检查 build 目录
if git ls-files --error-unmatch build/ >/dev/null 2>&1; then
    echo "❌ 警告：检测到 build/ 目录"
    ISSUES=$((ISSUES + 1))
fi

# 检查 .gradle 目录
if git ls-files --error-unmatch .gradle/ >/dev/null 2>&1; then
    echo "❌ 警告：检测到 .gradle/ 目录"
    ISSUES=$((ISSUES + 1))
fi

# 检查 .idea 目录
if git ls-files --error-unmatch .idea/ >/dev/null 2>&1; then
    echo "❌ 警告：检测到 .idea/ 目录"
    ISSUES=$((ISSUES + 1))
fi

# 检查 run 目录
if git ls-files --error-unmatch run/ >/dev/null 2>&1; then
    echo "❌ 警告：检测到 run/ 目录"
    ISSUES=$((ISSUES + 1))
fi

# 检查 .class 文件
if git ls-files | grep -q "\.class$"; then
    echo "❌ 警告：检测到 .class 文件"
    ISSUES=$((ISSUES + 1))
fi

# 检查 jar 文件（除了 gradle-wrapper）
if git ls-files | grep "\.jar$" | grep -v "gradle-wrapper\.jar" | grep -q .; then
    echo "❌ 警告：检测到不应提交的 .jar 文件"
    ISSUES=$((ISSUES + 1))
fi

if [ $ISSUES -eq 0 ]; then
    echo "✅ 未发现明显问题"
else
    echo ""
    echo "⚠️  发现 $ISSUES 个潜在问题"
    echo "请检查并移除不应提交的文件"
fi

echo ""
echo "=========================================="
echo "统计信息："
echo "=========================================="

# 统计文件类型
echo "Java 文件数量: $(git ls-files | grep "\.java$" | wc -l)"
echo "配置文件数量: $(git ls-files | grep -E "\.(toml|json|properties)$" | wc -l)"
echo "文档文件数量: $(git ls-files | grep -E "\.(md|txt)$" | wc -l)"

echo ""
echo "=========================================="
echo "检查完成"
echo "=========================================="

if [ $ISSUES -eq 0 ]; then
    echo "✅ 可以安全提交"
    exit 0
else
    echo "⚠️  请修复问题后再提交"
    exit 1
fi
