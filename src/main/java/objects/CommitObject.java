package objects;

import constants.StrConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitObject {
    private String committer;

    private String Timestamp;

    private String shaStr;

    private String parentShaStr;

    private String commitMessage;

    public final static String COMMIT_FILE_PREFIX = "commit";

    public static String getCommitObjectContentFromCommitObject(CommitObject commitObject){
        StringBuilder commitContent = new StringBuilder();
        commitContent.append("tree").append(StrConstant.SPACE).append(commitObject.shaStr).append(StrConstant.LINE_BREAK)
                .append("parent").append(StrConstant.SPACE).append(commitObject.parentShaStr).append(StrConstant.LINE_BREAK)
                .append("committer").append(StrConstant.SPACE).append(commitObject.committer).append(StrConstant.LINE_BREAK)
                .append("commit message").append(StrConstant.SPACE).append(commitObject.commitMessage);

        StringBuilder res = new StringBuilder();
        res.append(COMMIT_FILE_PREFIX)
                .append(StrConstant.SPACE)
                .append(commitContent.length())
                .append(StrConstant.OBJECT_NULL)
                .append(commitContent);

        return res.toString();
    }
}
