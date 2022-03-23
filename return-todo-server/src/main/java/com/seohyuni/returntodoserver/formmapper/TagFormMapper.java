package com.seohyuni.returntodoserver.formmapper;

import com.seohyuni.returntodoserver.form.TagForm;
import com.seohyuni.returntodoserver.model.Tag;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "TagFormMapperImpl",
    builder = @Builder(disableBuilder = true)
)
public abstract class TagFormMapper {

  public abstract List<TagForm.Output.GetAll> toGetAllList(List<Tag> in);

  public abstract Tag toTag(TagForm.Input.Add in);

  public abstract TagForm.Output.Get toGet(Tag in);

  public abstract TagForm.Output.GetAll toGetAll(Tag in);

}
