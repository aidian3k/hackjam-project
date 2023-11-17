import { Box, IconButton, TextField } from "@mui/material";
import SendIcon from "@mui/icons-material/Send";

export default function QuestionInput() {
  return (
    <Box display={"flex"} alignItems={"center"} gap={1}>
      <TextField fullWidth id="question" label="Question" variant="outlined" />
      <IconButton>
        <SendIcon />
      </IconButton>
    </Box>
  );
}
