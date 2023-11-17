import { Box, Paper, Typography } from "@mui/material";
import Image from "next/image";

export default function ProductInfo() {
  return (
    <Paper elevation={2} sx={{ p: 2 }}>
      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <Image
          src={"/img/product_img.jpg"}
          alt={"product"}
          width={80}
          height={80}
          style={{ borderRadius: 40 }}
        />
      </Box>
      <Box>
        <Typography textAlign={"center"} variant={"h6"} sx={{ ml: 2 }}>
          Chair
        </Typography>
        <Typography variant={"body1"}>
          The Nimbus boasts a sleek and modern design, with its gracefully
          curved armrests and minimalist silhouette. The frame, constructed from
          durable yet lightweight materials, ensures.
        </Typography>
      </Box>
    </Paper>
  );
}
