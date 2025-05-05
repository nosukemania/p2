#!/bin/bash

# アプリケーションバンドルの作成
mkdir -p Counter.app/Contents/MacOS
mkdir -p Counter.app/Contents/Resources

# Javaクラスファイルのコンパイル
javac CharacterCounter.java

# アイコンファイルのコピー（存在する場合）
if [ -f counter.icns ]; then
    cp counter.icns Counter.app/Contents/Resources/
fi

# Info.plistの作成
cat > Counter.app/Contents/Info.plist << EOF
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>CFBundleName</key>
    <string>counter</string>
    <key>CFBundleDisplayName</key>
    <string>counter</string>
    <key>CFBundleIdentifier</key>
    <string>com.example.counter</string>
    <key>CFBundleVersion</key>
    <string>1.0</string>
    <key>CFBundleShortVersionString</key>
    <string>1.0</string>
    <key>CFBundleIconFile</key>
    <string>counter.icns</string>
    <key>CFBundleExecutable</key>
    <string>counter</string>
    <key>CFBundlePackageType</key>
    <string>APPL</string>
    <key>NSHighResolutionCapable</key>
    <true/>
</dict>
</plist>
EOF

# 実行可能ファイルの作成
cat > Counter.app/Contents/MacOS/counter << EOF
#!/bin/bash
DIR="\$( cd "\$( dirname "\$0" )" && pwd )"
cd "\$DIR/.."
/usr/bin/java -cp . CharacterCounter
EOF

chmod +x Counter.app/Contents/MacOS/counter

echo "アプリケーションのビルドが完了しました。Counter.appが作成されました。" 