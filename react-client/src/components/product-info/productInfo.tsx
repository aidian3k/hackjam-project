import { Box, Paper, Typography } from "@mui/material";
import Image from "next/image";

export default function ProductInfo( { auction }: { auction: any }) {
  return (
    <Paper elevation={2}  sx={{ p: 2, height: '100%' }}>
      <Box
        display={"flex"}
        gap={1}
        alignItems={"center"}
        justifyContent={"end"}
        m={2}
      >
        <Typography textAlign={"center"} variant={"body2"}>
          Mike Perry
        </Typography>
        <Image
          src={"/img/avatar.jpg"}
          alt={"user"}
          width={20}
          height={20}
          style={{ borderRadius: 20 }}
        />
      </Box>
      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <Image
          src={auction.imageUrl}
          alt={"product"}
          width={160}
          height={160}
          style={{ borderRadius: 40 }}
        />
      </Box>
      <Box>
        <Typography
          textAlign={"center"}
          fontWeight={"bold"}
          variant={"h6"}
          mt={2}
          mb={1}
        >
            {auction.title}
        </Typography>
        <Typography variant={"body1"}>
          {/*The Nimbus boasts a sleek and modern design, with its gracefully*/}
          {/*curved armrests and minimalist silhouette. The frame, constructed from*/}
          {/*durable yet lightweight materials, ensures.*/}
            {auction.description}
        </Typography>
      </Box>
    </Paper>
  );
}
