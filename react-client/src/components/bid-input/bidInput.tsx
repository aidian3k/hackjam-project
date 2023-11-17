import { Box, Button, TextField } from "@mui/material";

export default function BidInput() {
  return (
    <Box>
      <Box display={"flex"} alignItems={"center"} gap={2} mt={2}>
        <TextField id="price" label="Price" variant="outlined" />
        <Button variant={"contained"} size={"large"} sx={{ height: "100%" }}>
          BID
        </Button>
      </Box>
      <Box display="flex" mt={1} gap={1}>
        <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
          100$
        </Button>
        <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
          200$
        </Button>
        <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
          300$
        </Button>
      </Box>
    </Box>
  );
}
