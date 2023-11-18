"use client"

import * as React from 'react';
import TextField from '@mui/material/TextField';
import { outlinedInputClasses } from '@mui/material/OutlinedInput';
import Box from '@mui/material/Box';
import { createTheme, ThemeProvider, Theme, useTheme } from '@mui/material/styles';
import Button from '@mui/material/Button';
import Autocomplete from '@mui/material/Autocomplete';
import { Chip } from '@mui/material';
import { TRACE_OUTPUT_VERSION } from 'next/dist/shared/lib/constants';

const customTheme = (outerTheme: Theme) =>
  createTheme({
    palette: {
      mode: outerTheme.palette.mode,
    },
    components: {
      MuiTextField: {
        styleOverrides: {
          root: {
            '--TextField-brandBorderColor': '#E0E3E7',
            '--TextField-brandBorderHoverColor': '#B2BAC2',
            '--TextField-brandBorderFocusedColor': '#6F7E8C',
            '& label.Mui-focused': {
              color: 'var(--TextField-brandBorderFocusedColor)',
            },
          },
        },
      },
      MuiOutlinedInput: {
        styleOverrides: {
          notchedOutline: {
            borderColor: 'var(--TextField-brandBorderColor)',
          },
          root: {
            [`&:hover .${outlinedInputClasses.notchedOutline}`]: {
              borderColor: 'var(--TextField-brandBorderHoverColor)',
            },
            [`&.Mui-focused .${outlinedInputClasses.notchedOutline}`]: {
              borderColor: 'var(--TextField-brandBorderFocusedColor)',
            },
          },
        },
      },
      MuiFilledInput: {
        styleOverrides: {
          root: {
            '&:before, &:after': {
              borderBottom: '2px solid var(--TextField-brandBorderColor)',
            },
            '&:hover:not(.Mui-disabled, .Mui-error):before': {
              borderBottom: '2px solid var(--TextField-brandBorderHoverColor)',
            },
            '&.Mui-focused:after': {
              borderBottom: '2px solid var(--TextField-brandBorderFocusedColor)',
            },
          },
        },
      },
      MuiInput: {
        styleOverrides: {
          root: {
            '&:before': {
              borderBottom: '2px solid var(--TextField-brandBorderColor)',
            },
            '&:hover:not(.Mui-disabled, .Mui-error):before': {
              borderBottom: '2px solid var(--TextField-brandBorderHoverColor)',
            },
            '&.Mui-focused:after': {
              borderBottom: '2px solid var(--TextField-brandBorderFocusedColor)',
            },
          },
        },
      },
    },
  });

function FinalAuction() {
  const outerTheme = useTheme();

  return (
    <Box
      sx={{
        display: 'block',
        width: '90vw',
        justifyItems: 'center',
      }}
    >
      <ThemeProvider theme={customTheme(outerTheme)} >
        <TextField fullWidth label="Price" sx={{
            display: 'block',
            margin: 5,
          }}/>
        <TextField multiline rows={4} fullWidth label="Description" sx={{
            display: 'block',
            margin: 5,
          }}/>
          <Autocomplete
      sx={{
        display: 'block',
        margin: 5,
      }}
      fullWidth
      multiple
      id="tags-standard"
      options={tags}
      getOptionLabel={(option) => option.tag}
      defaultValue={[tags[0]]}
      renderTags={(value, getTagProps) =>
        value.map((option, index) => (
          <Chip
            label={option.tag}
            {...getTagProps({ index })}
            sx={{
              backgroundColor: '#176B87', // Change to your desired background color
              color: 'white', // Change to your desired text color
            }}
          />
        ))
      }
      renderInput={(params) => (
        <TextField
          {...params}
          variant="standard"
          label="Multiple values"
          placeholder="Favorites"
        />
      )}
    />

    <div style={{ display: 'flex', width: '100vw', justifyContent: 'center' }}>
      <Button
        variant="contained"
        sx={{
          marginRight: 10,
          backgroundColor: '#64CCC5',
          '&:hover': {
            backgroundColor: '#4FA3A1',
          },
        }}
      >
        Add auction
      </Button>
      <Button
        variant="outlined"
        sx={{
          borderColor: '#64CCC5',
          color: '#64CCC5',
          '&:hover': {
            borderColor: '#4FA3A1',
            color: '#4FA3A1',
          },
        }}
      >
        Decline auction
      </Button>
    </div>
          <div style={{display: 'flex', width: '100vw', justifyContent: 'center'}}>
            <Button variant="contained" sx={{marginRight: 10, backgroundColor: '#64CCC5',
         '&:hover': {
          backgroundColor: '#4FA3A1', 
        } }}>Add auction</Button>
            <Button variant="outlined" sx={{marginRight: 10, backgroundColor: '#64CCC5',
         '&:hover': {
          backgroundColor: '#4FA3A1', 
        } }}>Decline auction</Button>
        </div>
          
      </ThemeProvider>
    </Box>
  ); 
}

const tags = [
  {tag: "Siema1"},
  {tag: "Siema2"},
  {tag: "Siema3"},
  {tag: "Siema4"},
  {tag: "Siema5"},
  {tag: "Siema6"},
]
export default FinalAuction;