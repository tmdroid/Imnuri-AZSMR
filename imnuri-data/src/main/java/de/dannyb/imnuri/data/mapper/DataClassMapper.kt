package de.dannyb.imnuri.data.mapper

interface DataClassMapper<INPUT, OUTPUT> {
    fun map(input: INPUT): OUTPUT
}