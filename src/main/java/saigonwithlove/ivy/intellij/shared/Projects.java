package saigonwithlove.ivy.intellij.shared;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.apache.maven.model.Model;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Projects {
  @NotNull
  public static List<Model> getIvyModels(@NotNull Project project) {
    return Arrays.stream(ModuleManager.getInstance(project).getModules())
        .filter(Modules::isMavenModel)
        .flatMap(module -> Modules.toMavenModel(module).map(Stream::of).orElseGet(Stream::empty))
        .filter(Modules::isIvyModel)
        .collect(Collectors.toList());
  }
}
