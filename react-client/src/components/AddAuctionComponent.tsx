import React, { ChangeEvent, FormEvent, useState } from "react";
import { Box, Button, TextField, Typography } from "@mui/material";
import axios from "axios";
import { DesktopDateTimePicker } from "@mui/x-date-pickers/DesktopDateTimePicker";

const AddAuctionComponent: React.FC = () => {
  const [file, setFile] = useState<File | null>(null);
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [localization, setLocalization] = useState<string>("");

  const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setFile(event.target.files[0]);
    }
  };

  const handleStartDateChange = (date: Date | null) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: Date | null) => {
    setEndDate(date);
  };

  const handleLocalizationChange = (event: ChangeEvent<HTMLInputElement>) => {
    setLocalization(event.target.value);
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData();
    if (file) {
      formData.append("image", file);
    }
    if (startDate) {
      formData.append("startDate", startDate.toISOString());
    }
    if (endDate) {
      formData.append("endDate", endDate.toISOString());
    }
    formData.append("localization", localization);

    try {
      const response = await axios.post("your-api-endpoint", formData);

      if (response.status === 200) {
        console.log("Success");
      } else {
        console.error("Failed");
      }
    } catch (error) {
      console.error("Error", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <Box
        display="flex"
        flexDirection="column"
        width="40%"
        mx="auto"
        textAlign="center"
        mt={8}
        p={6}
        gap={2}
        bgcolor="white"
        borderRadius={8}
        boxShadow={3}
      >
        <Typography variant="h6" className="mb-6 text-center">
          Add Auction
        </Typography>
        <div className="mb-4">
          <label
            htmlFor="image"
            className="block text-gray-700 text-sm font-bold mb-2"
          >
            Choose Image
          </label>
          <input
            type="file"
            id="image"
            onChange={handleFileChange}
            className="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </div>

        <DesktopDateTimePicker
          value={startDate}
          onChange={handleStartDateChange}
        />
        <DesktopDateTimePicker value={endDate} onChange={handleEndDateChange} />

        <TextField
          label="Localization"
          type="text"
          value={localization}
          onChange={handleLocalizationChange}
          className="mb-4"
        />

        <Button
          type="submit"
          variant="contained"
          color="primary"
          className="w-full"
        >
          Generate
        </Button>
      </Box>
    </form>
  );
};

export default AddAuctionComponent;
