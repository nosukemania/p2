#!/bin/bash

# アイコン用の一時ディレクトリを作成
mkdir -p icon.iconset

# 必要なサイズのアイコンを生成
sizes=(16 32 64 128 256 512 1024)
for size in "${sizes[@]}"; do
    # シンプルなアイコンを生成（ここでは文字"C"を表示）
    convert -size ${size}x${size} xc:white -gravity center \
        -pointsize $(($size/2)) -annotate 0 "C" \
        -fill black -stroke black \
        icon.iconset/icon_${size}x${size}.png
done

# .icnsファイルの作成
iconutil -c icns icon.iconset -o counter.icns

# 一時ディレクトリの削除
rm -rf icon.iconset

echo "アイコンファイル counter.icns が作成されました。" 