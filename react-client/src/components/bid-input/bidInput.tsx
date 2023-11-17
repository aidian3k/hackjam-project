import {Box, Button, Grid, TextField} from "@mui/material";

export default function BidInput() {
  return (
    <Box>
        <Grid container spacing={2} sx={{ flexGrow: 1 }}>
            <Grid item xs={3}></Grid>
            <Grid item xs={6} >
                <Box display={"flex"} alignItems={"center"} gap={2} mt={2} sx={{ display: "flex", justifyContent: "center" }}>
                    <TextField id="price" label="Price" variant="outlined" />
                    <Button variant={"contained"} size={"large"} sx={{ height: "100%" }}>
                        BID
                    </Button>
                </Box>
                <Box display="flex" mt={1} gap={1} sx={{ display: "flex", justifyContent: "center" }}>
                    <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
                        +100$
                    </Button>
                    <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
                        +200$
                    </Button>
                    <Button variant={"outlined"} sx={{ borderRadius: 8 }}>
                        +300$
                    </Button>
                </Box>
            </Grid>
            <Grid item xs={3}></Grid>
        </Grid>

    </Box>
  );
}
