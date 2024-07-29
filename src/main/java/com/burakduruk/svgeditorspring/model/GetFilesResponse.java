package com.burakduruk.svgeditorspring.model;

public record GetFilesResponse(
        String name,
        int numCirc,
        int numRect,
        int numGroups,
        int numPaths,
        int size
) {
}
