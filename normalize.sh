# Makes all tabls two spaces
find . -name '*.java' ! -type d -exec bash -c 'expand -t 4 "$0" > /tmp/e && mv /tmp/e "$0"' {} \;
# Removes trailing whitespace
find src -not \( -name .svn -prune -o -name .git -prune \) -type f -print0 | xargs -0 sed -i '' -E "s/[[:space:]]*$//"
