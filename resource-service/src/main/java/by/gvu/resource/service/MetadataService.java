package by.gvu.resource.service;

public interface MetadataService<I, O> {
    O readMetadata(I mp3FileModel);
}
