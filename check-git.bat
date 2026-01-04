@echo off
REM Git 提交前检查脚本 (Windows)
REM 用于验证将要提交的文件是否正确

echo ==========================================
echo Git 提交前检查
echo ==========================================
echo.

REM 检查是否在 Git 仓库中
if not exist .git (
    echo ❌ 错误：当前目录不是 Git 仓库
    echo 请先运行: git init
    exit /b 1
)

echo ✅ 检测到 Git 仓库
echo.

REM 检查 .gitignore 是否存在
if not exist .gitignore (
    echo ⚠️  警告：未找到 .gitignore 文件
) else (
    echo ✅ .gitignore 文件存在
)

echo.
echo ==========================================
echo 待提交的文件：
echo ==========================================

REM 显示将要提交的文件
git status --short

echo.
echo ==========================================
echo 检查潜在问题：
echo ==========================================

set ISSUES=0

REM 检查 build 目录
git ls-files | findstr /i "^build\\" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到 build/ 目录
    set /a ISSUES+=1
)

REM 检查 .gradle 目录
git ls-files | findstr /i "^\.gradle\\" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到 .gradle/ 目录
    set /a ISSUES+=1
)

REM 检查 .idea 目录
git ls-files | findstr /i "^\.idea\\" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到 .idea/ 目录
    set /a ISSUES+=1
)

REM 检查 run 目录
git ls-files | findstr /i "^run\\" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到 run/ 目录
    set /a ISSUES+=1
)

REM 检查 .class 文件
git ls-files | findstr /i "\.class$" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到 .class 文件
    set /a ISSUES+=1
)

REM 检查 jar 文件（除了 gradle-wrapper）
git ls-files | findstr /i "\.jar$" | findstr /v "gradle-wrapper\.jar" >nul 2>&1
if %errorlevel% equ 0 (
    echo ❌ 警告：检测到不应提交的 .jar 文件
    set /a ISSUES+=1
)

if %ISSUES% equ 0 (
    echo ✅ 未发现明显问题
) else (
    echo.
    echo ⚠️  发现 %ISSUES% 个潜在问题
    echo 请检查并移除不应提交的文件
)

echo.
echo ==========================================
echo 统计信息：
echo ==========================================

REM 统计文件类型
for /f %%i in ('git ls-files ^| findstr /i "\.java$" ^| find /c /v ""') do set JAVA_COUNT=%%i
for /f %%i in ('git ls-files ^| findstr /i "\.(toml^|json^|properties)$" ^| find /c /v ""') do set CONFIG_COUNT=%%i
for /f %%i in ('git ls-files ^| findstr /i "\.(md^|txt)$" ^| find /c /v ""') do set DOC_COUNT=%%i

echo Java 文件数量: %JAVA_COUNT%
echo 配置文件数量: %CONFIG_COUNT%
echo 文档文件数量: %DOC_COUNT%

echo.
echo ==========================================
echo 检查完成
echo ==========================================

if %ISSUES% equ 0 (
    echo ✅ 可以安全提交
    exit /b 0
) else (
    echo ⚠️  请修复问题后再提交
    exit /b 1
)
