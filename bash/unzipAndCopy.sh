for f in *.gz; do
    gzip -d $file
    cp "${file%.gz}" "${file%.gz}_13"
    rm "${file%.gz}"
done