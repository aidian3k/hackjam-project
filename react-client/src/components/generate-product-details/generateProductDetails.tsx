"use client";

import React, { FormEvent, useState } from "react";
import { generateAuctionDetails, saveImage } from "@/services/auction-service";
import { Box, Button } from "@mui/material";
import Typography from "@mui/material/Typography/Typography";
import { MuiFileInput } from "mui-file-input";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import Image from "next/image";

export default function GenerateProductDetails({
  setMedia,
  setAuctionDetails,
}: {
  setMedia: any;
  setAuctionDetails: any;
}) {
  const [file, setFile] = useState<File | null>(null);

  const handleChange = (newFile: File | null) => {
    if (!newFile) return;
    setFile(newFile);
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!file) return;

    const formData: FormData = new FormData();
    formData.append("file", file);
    saveImage(formData).then((media) => {
      setMedia(media);
      generateAuctionDetails(media).then((response) =>
        setAuctionDetails(response)
      );
    });
  };

  return (
    <Box>
      <Box width={"fit-content"} mb={3}>
        <Typography variant={"h5"}>Add auction</Typography>
        <Box sx={{ height: "1px", width: "100%", backgroundColor: "grey" }} />
      </Box>
      <form onSubmit={handleSubmit}>
        <Typography mb={1}>Product image</Typography>
        <Box display={"flex"} alignItems={"center"} gap={1}>
          <MuiFileInput
            variant="outlined"
            value={file}
            onChange={handleChange}
            InputProps={{ startAdornment: <AttachFileIcon /> }}
            title={"Choose product image"}
            placeholder={"Choose product image"}
          />
          {file && (
            <Image
              style={{ borderRadius: 10 }}
              src={file ? URL.createObjectURL(file) : ""}
              width={80}
              height={80}
              alt={"product image"}
            />
          )}
        </Box>
        <Box display={"flex"} flexDirection={"column"} gap={2} mt={2}>
          <Typography variant={"body2"} sx={{ color: "grey" }}>
            Please click button below to auto generate content based on the
            photo given above.
          </Typography>
          <Button type="submit" variant="contained" color="primary">
            Generate
          </Button>
        </Box>
      </form>
    </Box>
  );
}
