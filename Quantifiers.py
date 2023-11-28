import re

def main():
    expression = input("Enter the expression: ")
    analyzeExpression(expression)

def analyzeExpression(expression):
    parts = expression.split('|')

    boundedVar = extractVar(parts[0].strip())
    freeVar = extractVar(parts[1].strip())
    
    # remove bounded variables from free variables set
    freeVar.difference_update(boundedVar)

    print("Bounded Variables are:", boundedVar)
    print("Free Variables are:", freeVar)

def extractVar(seg):
    # exclude specific symbols and quantifiers
    excluded = {'+', '-', '*', '/', '∧', '∨', '∃', '∀', '<', '>', '=', '≤', '≥', '(', ')', ':', ','}
    
    
    # extract non-digit non-symbol characters
    vars_found = [var for var in re.findall(r'\b[a-zA-Z]\b', seg) if var not in excluded]
    
    return set(vars_found)

main()
