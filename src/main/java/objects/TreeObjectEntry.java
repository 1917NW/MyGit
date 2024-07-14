package objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TreeObjectEntry{
    public String mode;
    public String name;
    public byte[] shaBytes;
}
