package com.seohyuni.returntodoserver.mapper;

import com.seohyuni.returntodoserver.form.TagForm;
import com.seohyuni.returntodoserver.model.Tag;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

@Mapper
public abstract class TagMapper {

  public Tag update(TagForm.Input.Update in, Tag out) {
    if (in == null) {
      return null;
    }

    if (StringUtils.isNotEmpty(in.getName())) {
      out.setName(in.getName());
    }

    if (StringUtils.isNotEmpty(in.getColorCode())) {
      out.setColorCode(in.getColorCode());
    }

    return out;
  }


}
