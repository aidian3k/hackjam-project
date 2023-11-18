"use client"
import { Box, IconButton, TextField } from "@mui/material";
import SendIcon from "@mui/icons-material/Send";


export default function QuestionInput(props: any) {

  return (
    <Box display={"flex"} alignItems={"center"} gap={1}>
      <TextField fullWidth id="question" label="Question" variant="outlined" onChange={(e) => props.setCurrentValue(e.target.value)} value={props.value}/>
      <IconButton onClick={props.onClick}>
        <SendIcon />
      </IconButton>
    </Box>
  );
}
