import { Box, IconButton, Paper, Typography } from "@mui/material";
import QuestionInput from "@/components/product-questions/question-input/questionInput";
import HelpIcon from "@mui/icons-material/Help";

export default function ProductQuestions() {
  return (
    <Paper elevation={2} sx={{ p: 3 }}>
      <Box>
        <Box display={"flex"} alignItems={"center"}>
          <IconButton>
            <HelpIcon />
          </IconButton>
          <Typography variant={"h6"} fontWeight={"bold"}>
            Ask about product
          </Typography>
        </Box>
        <Typography ml={3} sx={{ color: "grey" }} mb={1}>
          No questions asked
        </Typography>
      </Box>

      <QuestionInput />
    </Paper>
  );
}
