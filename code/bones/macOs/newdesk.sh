#!/bin/bash
# 创建新桌面并切到新桌面
yabai -m space --create
id=$(yabai -m query --displays --display | grep "spaces")
yabai -m space --focus $(echo ${id:${#id}-3:1})





