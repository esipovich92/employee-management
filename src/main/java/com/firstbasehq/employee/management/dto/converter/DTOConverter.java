package com.firstbasehq.employee.management.dto.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Art Yesipovich 7/15/21
 */

public interface DTOConverter<FROM, TO> extends Converter<FROM, TO> {
    TO convert(FROM from);
}
