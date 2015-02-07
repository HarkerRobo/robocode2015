# Makes all tabls two spaces
find src ! -type d ! -name _tmp_ -exec sh -c 'expand -t 2 {} > _tmp_ && mv _tmp_ {}' \;
# Removes trailing whitespace
find src -not \( -name .svn -prune -o -name .git -prune \) -type f -print0 | xargs -0 sed -i '' -E "s/[[:space:]]*$//"
