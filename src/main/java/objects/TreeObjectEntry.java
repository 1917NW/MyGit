package objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeObjectEntry{

    public static final String EXECUTABLE_FILE = "100755";
    public static final String REGULAR_FILE = "100644";
    public static final String SYMBOLIC_LINK = "120000";
    public static final String DIRECTORIES = "040000";

    public String mode;
    public String name;
    public byte[] shaBytes;
}
