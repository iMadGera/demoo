package it.mad.demo.swagger;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class SwaggerPageable {

    @ApiParam(value = "Number of records per page", example = "0")
    @Nullable
    private Integer size;

    @ApiParam(value = "Results page you want to retrieve (0..N)", example = "0")
    @Nullable
    private Integer page;

    @ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    @Nullable
    private String sort;

}