package by.gvu.resource.service;

import by.gvu.resource.exception.ResourceServiceParseMetadataException;

public interface MetadataService<S, O> {
    O readMetadata(S mp3FileModel) throws ResourceServiceParseMetadataException;
}
